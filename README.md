# 💳 Razorpay Integration with Spring Boot MVC

This project demonstrates how to integrate Razorpay Payment Gateway in a Spring Boot MVC application. It supports payment creation, frontend checkout, and webhook handling for real-world eCommerce or service-based applications.

---

## 📦 Features

- 🔐 Secure Razorpay API integration
- 🧾 Order creation via Razorpay REST API
- 💳 Dynamic checkout page with Razorpay JavaScript SDK
- 📥 Webhook endpoint for payment success/failure
- ✅ Status tracking for payments
- 🧪 Test credentials ready for sandbox mode

---

## 🧰 Tech Stack

| Component         | Technology         |
|------------------|---------------------|
| Framework        | Spring Boot MVC     |
| Language         | Java 17             |
| Template Engine  | Thymeleaf (optional) |
| JSON Processing  | Jackson             |
| Build Tool       | Maven               |
| Payment Gateway  | Razorpay            |

---

## 🔧 Prerequisites

- Java 17+
- Maven
- Razorpay account (test mode or production)

---

## 🔑 Razorpay Configuration

Update `application.properties`:

```properties
razorpay.key_id=your_key_id
razorpay.key_secret=your_key_secret
razorpay.currency=INR
razorpay.company_name=Your Company Name
razorpay.logo_url=https://yourdomain.com/logo.png
--

# Clone the repo
git clone https://github.com/your-username/RazorPayIntegration.git
cd RazorPayIntegration

# Build and run
./mvnw spring-boot:run
