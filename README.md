# 🛒 WooCommerce Automation Testing

This project is a WooCommerce automated testing framework designed to validate core **WooCommerce** store functionalities using **Selenium WebDriver** and **TestNG**.
It focuses on verifying WooCommerce setup, checkout flow, tax configuration, payment methods, and essential user interactions on a local WordPress environment.

## 📋 Test Plan
![WooCommerce Automation Test Plan](https://github.com/mr-tanzim-ahmed/Woocommerce-Plugin-AutomationTesing/blob/main/WooCommerce.png)

## 📋 Prerequisites

Before running the project, ensure your environment is set up:

*   **OS:** Windows / Mac / Linux
*   **WordPress:** Latest version (Running via Local WP)
*   **WooCommerce:** Latest stable version
*   **Java:** JDK 17
*   **IDE:** IntelliJ IDEA
*   **Build Tool:** Gradle
*   **Browsers:** Chrome, Firefox

## ⚙️ Installation

**1. Clone the repository**
```bash
git clone https://github.com/mr-tanzim-ahmed/Woocommerce-Plugin-AutomationTesing.git
```
**2. Add Dependencies**

Ensure your `build.gradle` file includes the following:
```gradle
dependencies {
    implementation 'org.seleniumhq.selenium:selenium-java:4.25.0'
    testImplementation 'org.testng:testng:7.10.2'
    testImplementation 'com.aventstack:extentreports:5.1.2'
    testImplementation 'io.github.cdimascio:java-dotenv:5.2.2'
}
```

## 🏗️ Open the Project

1. Open **IntelliJ IDEA**.
2. Select **Open** and choose the cloned project folder.
3. Allow Gradle to sync and download dependencies.

---

## 🔧 WooCommerce Store Setup

Follow the steps below to prepare a fully functional test store:

1. Install **LocalWP** and create a new WordPress site.
2. Install and activate **WooCommerce**.
3. Install **Starter Templates** plugin.
4. Import an **Ecommerce Template** (auto-installs Astra Theme).
5. Choose **WordPress Block Editor** (default).
6. Import and activate **Organic Store Theme** (if not auto-activated).
7. Activate all required plugins.
8. Go to **Settings → Reading → Site Visibility → Live** → save changes.
9. Go to **WooCommerce → Settings → Payments** → enable all offline methods.
10. Go to **WooCommerce → Settings → General** → Enable Taxes.
11. Go to **WooCommerce → Settings → Tax:**
   - Set **Price Display Suffix:** `{price_including_tax}`
   - Go to **Standard Rates**
   - Add:
      - **Rate:** 5%
      - **Name:** VAT
12. Go to **WooCommerce Dashboard** → click **Launch Store**.
13. **Create Test User Accounts:**
   - **Admin Account for Testing:**
      - Go to **Users → Add New**
      - Username: `test_admin`
      - Email: `testadmin@example.com`
      - Role: **Administrator**
      - Set a strong password
      - Click **Add New User**
   - **Subscriber/Customer Account for Testing:**
      - Go to **Users → Add New**
      - Username: `test_customer`
      - Email: `testcustomer@example.com`
      - Role: **Subscriber** (or **Customer** if available)
      - Set a strong password
      - Click **Add New User**
14. **Verify Customer Order Flow:**
   - Log out from admin account
   - Visit the store frontend
   - Log in as `test_customer`
   - Add a product to cart
   - Proceed to checkout and place a test order
   - Navigate to **My Account → Orders**
   - Verify that order history displays correctly with order details
15. Visit the store homepage and confirm everything is working.

---

## 🔑 Configuration

> You can run tests using your own WooCommerce environment credentials. Configure your environment using either **Properties** or **.env**.

**Option A (Properties):**  
Update the `src/test/resources/config.properties` file with your environment and store data.

**Option B (.env):**  
Create a `.env` file in the root directory to store sensitive credentials safely.

**Required Configuration Values:**

1. **WordPress & Store Setup**
   - Admin page URL
   - Admin username
   - Admin password
   - Home page URL
   - Store page slug
   - Shop/Products page slug
   - Checkout page slug
   - My Account page slug
   - Cart page slug

2. **Test Data (E-commerce Specific)**
   - Test product name (e.g., "Test T-Shirt")
   - Test product description
   - Test product regular price (e.g., 29.99)
   - Test product sale price (optional, e.g., 19.99)
   - Test product SKU (e.g., "TST-001")
   - Test product category (e.g., "Clothing")
   - Test product stock quantity (e.g., 100)
   - Test tax rate (e.g., 5%)
   - Test shipping method (e.g., "Flat Rate")
   - Test coupon code (optional, e.g., "SAVE10")
   - Test customer email
   - Test customer first name
   - Test customer last name
   - Test billing address details

3. **Payment & Shipping Settings**
   - Enable Cash on Delivery (true/false)
   - Enable Direct Bank Transfer (true/false)
   - Enable Check Payments (true/false)
   - Default shipping zone
   - Default currency (e.g., USD, EUR, GBP)

4. **Browser Selection**
   - Browser to use: `chrome` or `firefox`

> Once these values are set, the tests will run on your local WooCommerce environment using the specified pages, credentials, and browser settings.

---

## 🚀 How to Run Tests

1. Navigate to the resources folder in the project view:  
   `src/test/resources/`
2. Locate the test suite file:  
   `TestCaseRunsuite.xml`
3. Right-click on the file and select **Run 'TestCaseRunsuite.xml'**.

---

## 🔄 CI/CD

The automation suite is integrated with **GitHub Actions**. Here's the workflow process:

1. **Trigger:** Runs automatically on push or pull requests to the `main` branch.
2. **Checkout Code:** Retrieves the latest code from the repository.
3. **Setup Environment:**
   - Installs **JDK 17**
   - Installs **Google Chrome** and **ChromeDriver**
   - Sets up **Gradle**
4. **Build Project:** Compiles the project using Gradle.
5. **Run Tests:** Executes the TestNG suite and generates **Extent Reports**.
6. **Serve Report (Optional):** Extent Reports can be served locally via a simple HTTP server for review.