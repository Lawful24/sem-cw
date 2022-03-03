# USE CASE: 28 View the Number of People in the World Who Speak English and the Percentage of the World Population Who Speak English

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *Linguist* I want *to view the number of people in the world who speak English, including the percentage of the world population who speak English* so that *I can provide a report to analytics*.

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the name of the language.

### Success End Condition

A report is available for analytics. The report includes the percentage of English speakers out of the total population.

### Failed End Condition

No report is produced. No percentage included.

### Primary Actor

Linguist.

### Trigger

A request for a language report is sent to Linguist from analytics.

## MAIN SUCCESS SCENARIO

1. Analytics request a popularity ranking of the top major languages spoken in the world.
2. Linguist extracts current number of people in the world who speak English.
3. Linguist utilises result to construct language popularity ranking.
4. Linguist provides report to analytics.

## EXTENSIONS

None.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0