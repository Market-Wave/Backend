# MarketWave

MarketWave is a multi-country online vehicle marketplace where individual sellers and professional dealers can publish vehicle advertisements, run branded store pages, and connect with buyers via search, chat, and preorder requests. The platform supports classic filter search, location-based discovery, and optional AI-powered semantic search so buyers can find the right vehicles even with natural-language queries.

---

## Features

### For Buyers

- Browse vehicle listings with rich details: brand, model, year, mileage, fuel, transmission, condition, price, and location.  
- Advanced filters: country, city, category, brand, model, body type, year range, mileage range, price range, fuel type, transmission, condition.  
- Location-based discovery using country/city plus latitude/longitude.  
- Optional semantic search using vector similarity (pgvector) to search by meaning instead of only exact keywords.  
- View detailed ad pages with photo galleries (front/back/side plus interior angles).  
- Like/save ads (favorites) and view a personal liked-ads list.  
- Real-time chat with sellers (conversation per ad) and message history.  
- Submit preorder/reservation requests on specific ads (with contact info, message, budget, optional deposit).  
- Create “Order Requests” (wanted ads) describing what vehicle they want; receive matches when suitable ads are listed.  
- “My Garage” to store owned vehicles and track estimated value based on marketplace data.

### For Sellers & Dealers

- Create, edit, and manage vehicle ads with status flow: Draft → Pending → Active → Sold/Expired/Rejected.  
- Upload structured media per ad with view tags: front/back/left/right, interior views, and extra angles.  
- View engagement metrics such as likes and (later) views and leads.  
- Create and manage **Store pages** acting as mini websites:  
  - Store profile with slug, description, and location.  
  - Custom branding assets: favicon, logo, banner, thumbnail.  
  - Fully customizable theme/page configuration stored as JSONB (colors, typography, layouts, sections, custom CSS/JS).  
  - Store gallery (multiple media items with sort order).  
  - Store inventory feed (all ads from that store).  
- Handle preorder inbox for their ads and respond through preorder message threads.  
- Run optional **bidding events** (auctions) on selected ads with start/end time, start price, reserve, and minimum increment.  
- Manage a personal garage for their own vehicles and price estimation.

### Platform & Monetization

- Multi-country support with pricing rules per country, category, and month.  
- Free ad quota per user per period; extra ads or store subscriptions can be purchased.  
- Online payments (via PayHere) for:  
  - Extra ad slots.  
  - Store subscription or premium seller features.  
- In-app notifications for:  
  - New chat messages.  
  - New preorder requests and updates.  
  - Order-request → ad matches.  
  - Payment success/failure.  
- Email notifications where appropriate (configurable per user).

### Content & SEO

- Blog module with posts, tags, slugs, and publication workflow (Draft/Published).  
- SEO-friendly URLs and per-store meta tags (title/description) via `store_page_configs`.

---

## Architecture Overview

MarketWave is designed to start as a modular monolith and later evolve into microservices with clear bounded contexts. Main domains:

- **Advertisement / Listing** – vehicle ads, media, likes, basic search.  
- **Store** – dealer store profiles, store page configuration, store media, store inventory.  
- **User & Auth** – users, profiles, settings, tokens.  
- **Chat** – conversations and messages between buyers and sellers.  
- **Preorder** – ad-level preorder requests and preorder messages.  
- **Order Requests & Matches** – wanted requests and their matches to ads.  
- **Bidding** – auction events and bids for selected ads.  
- **Garage** – user garage items and value estimates.  
- **Blog** – posts, tags, mappings.  
- **Payments** – payment records and gateway payloads.  
- **Notifications** – in-app and email notifications.  
- **Search / Vector Search** – filter search and AI semantic search backed by pgvector.

Typical request flow:

`Frontend → API Gateway/Ingress → Spring Boot controller → Service → Repository → PostgreSQL → back to client`,  
with Kafka events emitted for asynchronous tasks such as notifications and search indexing.

---

## Tech Stack

### Frontend

- React + TypeScript SPA.  
- React Query (or similar) for API data fetching.  
- WebSocket client for real-time chat.

### Backend

- Java 17+ with Spring Boot.  
- RESTful APIs using resource-oriented URIs, pagination, and filtering best practices.  
- Layered architecture: Controller → Service → Repository.

### Database

- PostgreSQL as primary relational database.  
- `pgcrypto` extension for UUID generation.  
- `pgvector` extension for storing embeddings and running vector similarity search on vehicle ads.

### Messaging / Events

- Apache Kafka for asynchronous events (ad created/updated, preorder created, payment completed, search index updates).

### File Storage

- Amazon S3 (or S3-compatible) for media files (ad photos, store banners/logos).  
- Pre-signed URL uploads so clients upload directly to S3 and backend stores the URLs only.

### Infrastructure / DevOps

- Docker images for each service.  
- Kubernetes (K8s) for deployment and scaling.  
- API Gateway / Ingress as single entry point.  
- CI/CD pipeline (GitHub Actions, GitLab CI, etc.) for build/test/deploy.

---

## Database Schema (Overview)

The project uses a PostgreSQL schema optimized for a vehicle marketplace with stores, chat, payments, preorders, and vector search.

Key tables (not exhaustive):

- **Users & auth**  
  - `users`, `user_settings`, `user_tokens`  
- **Stores**  
  - `stores`, `store_page_configs`, `store_media`  
- **Vehicle domain**  
  - `vehicle_brands`, `vehicle_models`, `body_types`, `ad_categories`  
  - `vehicle_ads`, `vehicle_media`, `vehicle_ad_likes`  
- **Marketplace logic**  
  - `user_ad_quota`, `pricing_rules`  
  - `order_requests`, `order_matches`  
  - `bidding_events`, `bids`  
- **Communication**  
  - `conversations`, `messages`  
  - `preorders`, `preorder_messages`  
  - `notifications`  
- **Extras**  
  - `garage_items`  
  - `blog_posts`, `blog_tags`, `blog_post_tags`  
  - `payments`  
  - `vehicle_ad_embeddings` (pgvector + IVFFlat index)  

The full schema is managed via SQL migrations (e.g., Flyway or Liquibase) and includes indexes for search, location, and vector similarity.

---

## Getting Started

1. **Clone the repository**  
   ```bash
   git clone https://github.com/Market-Wave/Backend.git
   cd marketwave
   ```

2. **Configure environment variables**  
   - PostgreSQL connection (URL, user, password).  
   - Kafka brokers.  
   - S3 credentials and bucket name.  
   - PayHere API keys and callback URLs.  

3. **Run database migrations**  
   - Apply the provided SQL schema via Flyway/Liquibase to your PostgreSQL instance.

4. **Start backend services**  
   - For MVP you can run a single Spring Boot app that includes advertisement, user, store, chat, preorder, and payment modules.

5. **Start frontend**  
   - Install dependencies and run the React app pointing to the API gateway base URL.

6. **(Optional) Enable vector search**  
   - Ensure `pgvector` extension is installed.  
   - Populate `vehicle_ad_embeddings` with embeddings for existing ads.  
   - Expose a semantic search endpoint that queries by vector similarity.

---

## Roadmap

- **Phase 1 (MVP)**  
  Users, ads CRUD, media upload, basic search, stores, chat, PayHere payments for extra ads.

- **Phase 2**  
  Preorder flows, order requests & matches, likes/favorites, blog, notifications.

- **Phase 3**  
  Bidding/auctions, My Garage price estimation, full vector search, complete microservice decomposition with Kafka events and separate search/notification/payment services.
