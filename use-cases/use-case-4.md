# USE CASE: 4 List the Top N Populated Countries in the World where the User Provides N

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *Logistics Manager* I want *to list the top N populated countries in the world where I provide N* so that *I can provide a priority report of Places of Destination for the shipment of our product.*

### Scope

Company.

### Level

Primary task.

### Preconditions

We know N. Database contains a list of all countries in the world.

### Success End Condition

A report is available for corporate.

### Failed End Condition

No report is produced. List of countries is not ordered. The amount of countries in the report is not equal to N.

### Primary Actor

Logistics Manager.

### Trigger

A request for a priority report with the top N number of countries is sent to logistics from corporate.

## MAIN SUCCESS SCENARIO

1. Corporate request a ranking of all countries in the world based on population.
2. Logistics Manager defines the number of countries to be included in the report.
3. Logistics Manager extracts current population information of pre-defined number of countries in the world.
4. Logistics Manager provides report to corporate.

## EXTENSIONS

None.

## SUB-VARIATIONS

1. Logistics Manager defines ascending order.
2. Logistics Manager defines descending order.

## SCHEDULE

**DUE DATE**: Release 1.0