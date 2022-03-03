# USE CASE: 6 List the Top N Populated Cities in the world Where I Provide N

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *Population Analyst* I want to *list the top N populated cities in the world where I provide N* so that I can create a report.

### Scope

Company.

### Level

Primary task.

### Preconditions

We provided N. Database contains a list of all cities in the world.

### Success End Condition

A report is available for corporate. Information in report is in descending order.

### Failed End Condition

No report is produced. List of cities is not ordered.

### Primary Actor

Population Analyst.

### Trigger

A request for a report of city population of the world is sent to Analyst from corporate.

## MAIN SUCCESS SCENARIO

1. Corporate requests a ranking of cities in the world based on population.
2. Population Analyst extracts current population information of all cities in the world.
3. Population Analyst provides report to corporate.

## EXTENSIONS

None.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0