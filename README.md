# Java Statistics Calculator and Visualizer

A Java Swing desktop application for statistical analysis, frequency tables, saved experiments, and custom visual charts. The project is built with core Java and Java 2D graphics, without external charting libraries.

## Features

- Multi-type data support:
  - Raw numeric samples.
  - Grouped data using classes and frequencies.
  - Qualitative string samples.
- Statistical calculations:
  - Mean, median, and mode.
  - Quartiles Q1, Q2, and Q3.
  - Separate raw-sample methods and grouped-data methods, so each section calculates statistics in the proper way.
  - Frequency, class boundaries, midpoints, relative frequency, and cumulative frequencies.
- Full cumulative-frequency table details:
  - Ascending cumulative frequency.
  - Descending cumulative frequency.
  - Less-than cumulative frequency.
  - Greater-than cumulative frequency.
  - Tables include the needed extra row beyond the class count for cumulative endpoints.
- Custom chart rendering:
  - Pie chart.
  - Frequency histogram.
  - Frequency polygon.
  - Ogive.
- Experiment management:
  - Save experiments locally.
  - Prevent repeated experiment names.
  - View previous experiments in History.
  - Open the statistics screen directly from a selected history item.
- Modern Swing UI:
  - Dashboard navigation with `CardLayout`.
  - Back buttons for returning from statistics screens.
  - Settings page.
  - Night mode support.
  - Shared theme styling for panels, buttons, tables, history, settings, and statistics views.

## Technical Architecture

The project is organized into focused packages under `src`.

- `StatisCalc`
  - Contains the statistical logic.
  - `Quan` is the shared base class for quantitative calculations.
  - `QuanSambles` handles raw numeric samples.
  - `QuanWithClassesAndFreq` handles grouped class-frequency data.
  - `QualitaveSambles` handles qualitative samples.
  - `StatisTools` provides shared helper methods.
- `UIData`
  - Converts calculated statistics into table-ready datasets.
  - Uses `ArrayList`-based data structures for clearer and safer data handling.
  - Keeps presentation data separate from calculation logic.
- `Charts`
  - Contains custom Swing chart components.
  - Each chart draws itself by overriding `paintComponent`.
- `UI`
  - Contains the Swing screens and navigation.
  - `ui` controls the main frame and page switching.
  - `HomePanel`, `InputPanel`, `HistoryPanel`, `SettingsPanel`, and `StatisPanel` provide the main user workflow.
  - `Theme` centralizes light and night mode colors.
- `storage`
  - Stores saved experiments.
  - Prevents duplicate experiment names.

## How It Works

1. The user selects the experiment type and enters data.
2. The input screen validates the data and prevents duplicate experiment names.
3. The experiment is saved.
4. The correct calculation class processes the data:
   - Raw samples use raw sample statistics.
   - Grouped data uses grouped formulas.
   - Qualitative data uses category frequencies.
5. `StatisticsDataset` prepares the results for tables.
6. The UI shows tables and charts.
7. Saved experiments can be reopened from History.
