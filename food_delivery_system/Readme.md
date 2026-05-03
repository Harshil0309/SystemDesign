# 🍔 Food Delivery System – Low Level Design (LLD)

---

## 🚀 Overview

This project is a Low-Level Design (LLD) of a food delivery system similar to Swiggy/Zomato.

It covers:
- User management
- Restaurant & menu management
- Order lifecycle
- Rider assignment

---

## 🧱 Architecture

Main (Orchestrator)  
→ Services (Business Logic)  
→ Repositories (Storage)  
→ Entities (Models)

---

## 🧑‍💻 Entities

### 👤 User

Fields:
- id
- name
- address
- contact

Methods:
- getId()
- getName()
- getAddress()
- getContact()
- updateName()
- updateAddress()
- updateContact()

---

### 🛵 Rider

Fields:
- id
- name
- contact
- isAvailable
- currentOrderId

Methods:
- getId()
- getName()
- getContact()
- isAvailable()
- assignOrder(orderId)
- completeOrder()
- setAvailability(boolean)

---

### 🍽️ Restaurant

Fields:
- id
- name
- address
- contact
- Map<String, MenuItem> menu

Methods:
- getId()
- getName()
- getItemById(itemId)
- getMenu()
- addMenuItem(MenuItem)
- removeMenuItem(itemId)

---

### 🍕 MenuItem

Fields:
- id
- name
- description
- price
- quantity
- isAvailable
- restaurant

Methods:
- getId()
- getName()
- getPrice()
- getQuantity()
- getIsAvailable()
- setPrice()
- setQuantity()
- setIsAvailable()

---

### 📦 Order

Fields:
- id
- List<OrderItem> items
- OrderStatus status
- userId
- restaurantId
- riderId
- billAmount

Methods:
- acceptOrder()
- startPreparing()
- markOutForDelivery()
- markDelivered()
- rejectOrder()
- assignRider(riderId)

---

### 🧾 OrderItem

Fields:
- id
- name
- price
- quantity
- restaurantId

Methods:
- getId()
- getName()
- getPrice()
- getQuantity()

---

### 📥 ItemRequest (DTO)

Fields:
- itemId
- quantity

---

### 📊 OrderStatus (Enum)

CREATED  
ACCEPTED  
PREPARING  
OUTFORDELIVERY  
DELIVERED  
REJECTED  

---

## 🧠 Services

### 👤 UserService

Methods:
- createUser(name, address, contact)
- updateDetails(userId, name, address, contact)
- getUser(userId)
- getUserByContact(contact)

---

### 🛵 RiderService

Methods:
- createRider(name, contact)
- setAvailability(riderId, availability)
- assignRider(orderId) → returns riderId
- completeDelivery(orderId)

---

### 🍽️ RestaurantService

Methods:
- createRestaurant(name, address, contact)
- getRestaurantById(restaurantId)
- addMenuItem(restaurantId, name, desc, price, quantity)
- removeMenuItem(restaurantId, itemId)
- updateMenuItem(restaurantId, itemId, name, desc, price)
- updateItemQuantity(restaurantId, itemId, quantity)
- setItemAvailability(restaurantId, itemId, isAvailable)
- getMenuItem(restaurantId, itemId)
- getMenu(restaurantId)

---

### 📦 OrderService

Core Method:
- placeOrder(userId, restaurantId, List<ItemRequest>)

Flow:
validate input  
→ fetch restaurant  
→ validate items  
→ rebuild OrderItems (trusted data)  
→ calculate bill  
→ create order  
→ save order  

Other Methods:
- getOrderById(orderId)
- acceptOrder(orderId)
- startPreparing(orderId) → assign rider
- markOutForDelivery(orderId)
- markDelivered(orderId) → free rider
- rejectOrder(orderId) → free rider if assigned

---

## 🗄️ Repositories

### 👤 UserRepository

Map<String, User>

Methods:
- save(user)
- getUser(userId)
- getUserByContact(contact)
- isContactUnique(contact)

---

### 🛵 RiderRepository

Map<String, Rider>

Methods:
- save(rider)
- getRider(riderId)
- getRiderByContact(contact)
- getAvailableRider()
- getRiderByOrderId(orderId)

---

### 🍽️ RestaurantRepository

Map<String, Restaurant>

Methods:
- save(restaurant)
- getRestaurantById(id)
- exists(id)
- delete(id)
- getAll()

---

### 📦 OrderRepository

Map<String, Order>

Methods:
- save(order)
- getOrderById(orderId)
- exists(orderId)
- delete(orderId)
- getAll()

---

## 🔁 End-to-End Flow

Create User  
→ Create Restaurant  
→ Add Menu Items  
→ Create Rider  

→ placeOrder()  
→ acceptOrder()  
→ startPreparing() → assign rider  
→ markOutForDelivery()  
→ markDelivered()  

---

## ⚠️ Key Design Decisions

1. DTO (ItemRequest)
- Prevents price tampering
- Minimal input

2. OrderItem vs MenuItem
- OrderItem = snapshot at order time
- MenuItem can change later

3. Service-to-Service Communication
OrderService → RestaurantService → Repository

4. Map over List
- O(1) lookup
- Scalable

5. Repository Layer
- Separation of concerns
- Easy DB integration

---

## 🔥 Edge Cases Handled

- Invalid input
- Item not found
- Insufficient quantity
- Duplicate contact
- No rider available
- Order not found

---

## 🚀 Future Improvements

- Smart rider assignment
- Order queueing system
- Async processing
- Payment integration
- Location-based search
- Ratings & reviews
- Caching

---

## 🧠 How to Rebuild This System

1. Create Entities  
2. Create Repositories (Map-based)  
3. Create Services  
4. Add validations  
5. Implement order flow  
6. Add orchestrator (Main)  
7. Add logging  

---

## 🎯 Final Takeaway

This system demonstrates:
- Clean architecture
- Separation of concerns
- Real-world modeling
- Scalable design thinking

---

💡 Tip:
If you revisit later, follow:

Entities → Repositories → Services → Flow