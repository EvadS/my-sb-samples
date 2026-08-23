// seed.js
db = db.getSiblingDB("school");
db.students.insertMany([
    { name: "Gbenga Oyatoye", age: 22, major: "Computer Science" },
    { name: "John Doe", age: 24, major: "Mathematics" },
    { name: "Jimmy Azar", age: 28, major: "Physics" }
]);