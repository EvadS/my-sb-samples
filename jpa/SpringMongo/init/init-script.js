// Create additional database, collections, and documents
db = db.getSiblingDB('springmongo');  // Switch to the new database

// Create a collection and insert sample data
// db.createCollection('users');
// db.users.insertMany([
//   { name: "Alice", age: 25 },
//   { name: "Bob", age: 30 }
// ]);

print("✅ Database and sample data created successfully!");
