# USE CASE: 41 Provide the Number of People who Speak the Following Languages of the World from Greatest Number to Smallest, including the Percentage of the World Population who Speak the Language: Chinese, English, Hindi, Spanish, Arabic

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *Linguist* I want *to provide the number of people who speak the following languages of the world from greatest number to smallest, including the percentage of the world population who speak the language: Chinese, English, Hindi, Spanish, Arabic* so that *I can provide a language popularity ranking to analytics*.

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the names of the languages.

### Success End Condition

A report is available for analytics. The report includes the percentage of speakers out of the total population. The list of languages is in descending order.

### Failed End Condition

No report is produced. No percentage included. List not ordered.

### Primary Actor

Linguist.

### Trigger

A request for a language popularity ranking report is sent to Linguist from analytics.

## MAIN SUCCESS SCENARIO

1. Analytics request a popularity ranking of the top major languages spoken in the world.
2. Linguist defines the language.
3. Linguist extracts ordered list of people in the world who speak the defined languages.
4. Linguist provides report to analytics.

## EXTENSIONS

None.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0