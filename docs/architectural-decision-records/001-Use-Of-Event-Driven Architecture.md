# Use of Event-Driven Architecture

## Context and Problem Statement

The goal of BankBase is to create a robust, scalable, and modular banking system that 
can handle various financial operations without being constrained by specific country 
rules or regulations. Given the complexity and the need for flexibility, choosing the 
right architecture is crucial.

## Decision

We have decided to use an event-driven architecture for our microservice-based banking 
system.

## Schema

Below is the schema of our chosen architecture:

```
                                   +---------------------+
                                   |     Frontend        |
                                   +----------+----------+
                                              |
                                              v
                                   +----------+----------+
                                   |   Gateway Service   |
                                   +----------+----------+
                                              |
                                              v
                                   +----------+----------+
                                   |   Event Broker      |
                                   +----------+----------+
                                              |
        +---------+-----------+-----------+----------+-----------+-----------+
        |         |           |           |          |           |           |
        v         v           v           v          v           v           v
+-------+--+  +---+---+  +----+----+  +---+---+  +----+----+  +---+---+  +----+----+
|Account   |  |Backup |  |Deposit   |  |Investment|  |Loan     |  |Notification|  |Payment   |
|Service   |  |Service|  |Service   |  |Service   |  |Service  |  |Service     |  |Service   |
+-------+--+  +-------+  +----+----+  +-------+--+  +----+----+  +-------+--+  +----+----+
        |                  |           |           |           |             |
        v                  v           v           v           v             v
+-------+--+  +------------+-----------+-----------+-----------+-------------+
| Profile   |  |                             Transaction Service              |
| Service   |  +-------------------------------------------------------------+
+-------+---+   
        |
        v
+-------+--+
| Support   |
| Service   |
+-------+--+
```

## Explanation and Rationale

### Loose Coupling and Scalability

An event-driven architecture allows for loose coupling between services. Each service
operates independently and communicates through events. This decoupling is beneficial
for several reasons:

1. Scalability: Services can be scaled independently based on their load. For example,
if the Transaction Service is heavily loaded, it can be scaled without affecting other
services.

2. Resilience: Failures in one service do not directly impact others, as long as the 
event broker is functioning. This improves the overall fault tolerance of the system.

3. Flexibility: New services can be added without significant changes to existing 
services. They simply need to subscribe to relevant events.

## Asynchronous Processing

Event-driven systems support asynchronous processing, which can enhance the performance
and responsiveness of the application. Services can publish events and continue processing
other tasks without waiting for a response.

## Real-time Updates

Banking applications often require real-time updates, especially for transactions. An 
event-driven approach ensures that all interested services receive updates as soon as 
events are published. This is crucial for maintaining an accurate and up-to-date state 
across all services.

## Examples of Interactions

### Account Service:

- Publishes events for account creation, updates, and deletions.
- Subscribes to transaction events to update account balances.

### Deposit Service:

- Publishes deposit events.
- Subscribes to transaction events to reflect deposits in account balances.

### Notification Service:

-Subscribes to various events to send notifications (e.g., deposit made, transaction
completed).

## Event Broker

Kafka is chosen as the event broker for several reasons:

1. High Throughput: Kafka can handle large volumes of events per second, making it 
suitable for a high-transaction banking system.

2. Scalability: Kafka can be scaled horizontally to handle increasing loads, ensuring 
the system remains responsive under high demand.

3. Durability: Kafka ensures data durability by persisting messages, providing a reliable 
messaging system.

4. Flexibility: Kafka supports a variety of messaging patterns (e.g., pub-sub, queueing), 
making it versatile for different use cases.

## Trade-offs

### Complexity

While event-driven architecture offers many benefits, it also introduces complexity:

1. Event Management: Managing and tracking events can become complex, especially as the
number of services grows.

2. Eventual Consistency: The system may exhibit eventual consistency rather than strong
consistency, which can be a challenge for certain operations.

### Monitoring and Debugging

Debugging issues in an event-driven system can be more challenging compared to a 
synchronous system. It requires robust monitoring and logging to trace events and 
understand the flow of data.

## Conclusion

The event-driven architecture is chosen for BankBase to achieve a scalable, flexible,
and resilient banking system. Despite its complexity, the benefits of loose coupling,
asynchronous processing, and real-time updates align well with the requirements of a
modern banking application.

This approach ensures that BankBase can efficiently handle various financial operations,
provide real-time updates, and scale as needed, making it a robust foundation for a base
banking system.
