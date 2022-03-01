# USE CASE: 2 List All the Countries in a Continent Organised by Largest Population to Smallest

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *Logistics Manager* I want *to list all the countries in a continent organised by largest population to smallest* so that *I can provide a priority report of Places of Destination for the shipment of our product.*

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the continent. Database contains a list of all countries in given continent.

### Success End Condition

A report is available for corporate. Information in report is in descending order.

### Failed End Condition

No report is produced. List of countries is not ordered.

### Primary Actor

Logistics Manager.

### Trigger

A request for a priority report of countries in a continent is sent to logistics from corporate.

## MAIN SUCCESS SCENARIO

1. Corporate request a ranking of all countries in the given continent based on population.
2. Logistics Manager captures name of continent to get population ranking for.
3. Logistics Manager extracts current population information of all countries in the given continent.
4. Logistics Manager provides report to corporate.

## EXTENSIONS

1. Continent does not exist:
   i. Logistics Manager informs finance no such region exists.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0