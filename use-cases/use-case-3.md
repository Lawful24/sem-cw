# USE CASE: 3 List All the Countries in a Region Organised by Largest Population to Smallest

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *Logistics Manager* I want *to list all the countries in a region organised by largest population to smallest* so that *I can provide a priority report of Places of Destination for the shipment of our product.*

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the region. Database contains a list of all countries in given region.

### Success End Condition

A report is available for corporate. Information in report is in descending order.

### Failed End Condition

No report is produced. List of regions is not ordered.

### Primary Actor

Logistics Manager.

### Trigger

A request for a priority report of countries in a region is sent to logistics from corporate.

## MAIN SUCCESS SCENARIO

1. Corporate request a ranking of all countries in the given region based on population.
2. Logistics Manager captures name of region to get population ranking for.
3. Logistics Manager extracts current population information of all countries in the given region.
4. Logistics Manager provides report to corporate.

## EXTENSIONS

1. Region does not exist:
   i. Logistics Manager informs finance no such region exists.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0