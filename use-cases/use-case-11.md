# USE CASE: 11 List the Top N Populated Countries in a Region Where I Provide N

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *Population Analyst* I want to *list the top N populated countries in a region where I provide N* so that I can create a report.

### Scope

Company.

### Level

Primary task.

### Preconditions

We provided N. We know the region. Database contains a list of all countries in the world.

### Success End Condition

A report is available for corporate. Information in report is in descending order.

### Failed End Condition

No report is produced. List of countries is not ordered.

### Primary Actor

Population Analyst.

### Trigger

A request for a report of country population per region is sent to Analyst from corporate.

## MAIN SUCCESS SCENARIO

1. Corporate requests a ranking of countries in a region based on population.
2. Population Analyst extracts current population information of all countries in the region.
3. Population Analyst provides report to corporate.

## EXTENSIONS

None.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0