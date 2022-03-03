# USE CASE: 5 List All the Cities in a District Organised by Largest Population to Smallest

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *Logistics Manager* I want *to list all the cities in a district organised by largest population to smallest.* so that *I can provide a priority report of Places of Destination for the shipment of our product.*

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the district. Database contains a list of all cities in given district.

### Success End Condition

A report is available for corporate. Information in report is in descending order.

### Failed End Condition

No report is produced. List of cities is not ordered.

### Primary Actor

Logistics Manager.

### Trigger

A request for a priority report of cities in a district is sent to logistics from corporate.

## MAIN SUCCESS SCENARIO

1. Corporate request a ranking of all cities in the given district based on population.
2. Logistics Manager captures name of district to get population ranking for.
3. Logistics Manager extracts current population information of all countries in the given region.
4. Logistics Manager provides report to corporate.

## EXTENSIONS

1. District does not exist:
   i. Logistics Manager informs corporate no such region exists.
2. District does not contain any cities:
   i. Logistics Manager informs corporate district is empty.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0