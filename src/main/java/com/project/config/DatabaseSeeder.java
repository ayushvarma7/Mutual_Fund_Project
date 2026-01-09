package com.project.config;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.project.model.Investment;
import com.project.model.Investor;
import com.project.model.MutualFund;
import com.project.model.PortfolioManager;
import com.project.model.Stock;
import com.project.model.StockIdentifier;
import com.project.model.StocksInFund;
import com.project.repository.InvestmentRepository;
import com.project.repository.InvestorRepository;
import com.project.repository.MutualFundRepository;
import com.project.repository.PortfolioManagerRepository;
import com.project.repository.StockRepository;
import com.project.repository.StocksInFundRepository;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private MutualFundRepository mutualFundRepository;

    @Autowired
    private InvestorRepository investorRepository;

    @Autowired
    private PortfolioManagerRepository portfolioManagerRepository;

    @Autowired
    private StocksInFundRepository stocksInFundRepository;

    @Autowired
    private InvestmentRepository investmentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Faker faker = new Faker();
    private final Random random = new Random();

    @Override
    public void run(String... args) throws Exception {
        if (investorRepository.count() > 0) {
            System.out.println("Database already seeded. Skipping mock data generation.");
            return;
        }

        System.out.println("Seeding database with mock data...");

        // 1. Create Portfolio Managers
        List<PortfolioManager> managers = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            PortfolioManager manager = new PortfolioManager();
            manager.setManagerId(100 + i); // Manual ID as it's not generated
            manager.setFirstName(faker.name().firstName());
            manager.setLastName(faker.name().lastName());
            manager.setEmail(faker.internet().emailAddress());
            manager.setContactNumber(faker.phoneNumber().cellPhone());
            managers.add(portfolioManagerRepository.save(manager));
        }
        System.out.println("Seeded " + managers.size() + " Portfolio Managers.");

        // 2. Create Stocks
        List<Stock> stocks = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Stock stock = new Stock();
            String ticker = faker.stock().nsdqSymbol();
            stock.setStockTicker(ticker);
            double open = 50 + random.nextDouble() * 450; // 50 to 500
            stock.setOpeningPrice(Math.round(open * 100.0) / 100.0);
            stock.setLowPrice(Math.round(stock.getOpeningPrice() * 0.95 * 100.0) / 100.0);
            stock.setHighPrice(Math.round(stock.getOpeningPrice() * 1.05 * 100.0) / 100.0);
            double close = stock.getLowPrice() + random.nextDouble() * (stock.getHighPrice() - stock.getLowPrice());
            stock.setClosingPrice(Math.round(close * 100.0) / 100.0);
            stock.setDateOfRecord(faker.date().past(30, TimeUnit.DAYS));
            stocks.add(stockRepository.save(stock));
        }
        System.out.println("Seeded " + stocks.size() + " Stocks.");

        // 3. Create Mutual Funds
        List<MutualFund> funds = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MutualFund fund = new MutualFund();
            fund.setFundName(faker.company().name() + " Fund");
            fund.setAssetsUnderManagement(1000000 + random.nextDouble() * 49000000);
            fund.setCurrentNAV(Math.round((10 + random.nextDouble() * 190) * 100.0) / 100.0);
            fund.setExpenseRatio(Math.round((0.5 + random.nextDouble() * 2.0) * 100.0) / 100.0);
            fund.setExitLoad(Math.round((0.1 + random.nextDouble() * 0.9) * 100.0) / 100.0);
            fund.setInceptionDate(faker.date().past(365 * 5, TimeUnit.DAYS));

            // Assign random manager
            PortfolioManager manager = managers.get(random.nextInt(managers.size()));
            fund.setManagerId(manager.getManagerId());

            funds.add(mutualFundRepository.save(fund));
        }
        System.out.println("Seeded " + funds.size() + " Mutual Funds.");

        // 4. Assign Stocks to Funds
        for (MutualFund fund : funds) {
            int numberOfStocks = 3 + random.nextInt(3); // 3 to 5 stocks per fund
            for (int i = 0; i < numberOfStocks; i++) {
                Stock stock = stocks.get(random.nextInt(stocks.size()));

                // Create primary key
                StockIdentifier identifier = new StockIdentifier(fund.getFundId(), stock.getStockID());

                // Check if already exists to avoid duplicates
                if (!stocksInFundRepository.existsById(identifier)) {
                    StocksInFund stockInFund = new StocksInFund();
                    stockInFund.setIdentifier(identifier);
                    stockInFund.setFund(fund);
                    stockInFund.setStock(stock);
                    stockInFund.setStockWeight(Math.round((5 + random.nextDouble() * 25) * 100.0) / 100.0); // 5 to 30
                    stockInFund.setUnit(Math.round((100 + random.nextDouble() * 900) * 100.0) / 100.0);
                    stocksInFundRepository.save(stockInFund);
                }
            }
        }
        System.out.println("Seeded Stocks in Funds.");

        // 5. Create Investors
        List<Investor> investors = new ArrayList<>();

        // Create a demo admin user
        Investor admin = new Investor();
        admin.setFirstName("Admin");
        admin.setLastName("User");
        admin.setEmail("admin@example.com");
        admin.setPassword(passwordEncoder.encode("password"));
        admin.setContactNumber("1234567890");
        admin.setRole("ROLE_ADMIN");
        investors.add(investorRepository.save(admin));

        // Create a demo regular user
        Investor user = new Investor();
        user.setFirstName("Demo");
        user.setLastName("User");
        user.setEmail("user@example.com");
        user.setPassword(passwordEncoder.encode("password"));
        user.setContactNumber("0987654321");
        user.setRole("ROLE_USER");
        investors.add(investorRepository.save(user));

        // Create random investors
        for (int i = 0; i < 8; i++) {
            Investor investor = new Investor();
            investor.setFirstName(faker.name().firstName());
            investor.setLastName(faker.name().lastName());
            investor.setEmail(faker.internet().emailAddress());
            investor.setPassword(passwordEncoder.encode("123456"));
            investor.setContactNumber(faker.phoneNumber().cellPhone());
            investor.setRole("ROLE_USER");
            investors.add(investorRepository.save(investor));
        }
        System.out.println("Seeded " + investors.size() + " Investors.");

        // 6. Create Investments
        for (Investor investor : investors) {
            int numberOfInvestments = 1 + random.nextInt(3); // 1 to 3 investments per investor
            for (int i = 0; i < numberOfInvestments; i++) {
                MutualFund fund = funds.get(random.nextInt(funds.size()));

                Investment investment = new Investment();
                investment.setInvestor(investor);
                investment.setFund(fund);
                investment.setAmountInvested(1000 + random.nextDouble() * 49000);
                investment.setUnits(investment.getAmountInvested() / fund.getCurrentNAV());
                investment.setDateOfInvestment(faker.date().past(365, TimeUnit.DAYS));
                investment.setTransactionType(random.nextBoolean() ? "BUY" : "SIP");

                investmentRepository.save(investment);
            }
        }
        System.out.println("Seeded Investments.");

        System.out.println("Database seeding completed successfully!");
    }
}
