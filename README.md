# Java Statistics Calculator and Visualizer

A Java Swing desktop application for statistical analysis, frequency tables, saved experiments, and custom visual charts. The project is built with core Java, Swing, and Java 2D graphics, without external charting libraries.

The application supports raw numeric samples, grouped class-frequency data, and qualitative data. It separates statistical logic from UI rendering, stores experiments locally, and provides a modern interface with history navigation and night mode.

## Key Features

### Statistical Analysis

- Raw numeric sample analysis.
- Grouped data analysis using classes and frequencies.
- Qualitative data analysis for string-based samples.
- Ordinal qualitative support with cumulative frequencies.
- Mean, median, and mode.
- Quartiles Q1, Q2, and Q3.
- Relative frequency.
- Class boundaries.
- Midpoints.
- Ascending cumulative frequency.
- Descending cumulative frequency.
- Less-than cumulative frequency.
- Greater-than cumulative frequency.

### Correct Method Separation

Each data type uses the correct statistical method for its structure:

- Raw numeric samples use raw sample methods for mean, median, mode, and quartiles.
- Grouped class-frequency data uses grouped formulas such as weighted mean, grouped median, grouped mode, and grouped quartiles.
- Qualitative nominal data shows categories and frequencies.
- Qualitative ordinal data also shows cumulative frequency details.

### Complete Frequency Tables

Cumulative-frequency tables include the extra endpoint row needed to show full details beyond the class count. This prevents ascending, descending, less-than, and greater-than cumulative frequency values from being cut off.

### Custom Visualizations

Charts are hand-rendered with Swing and Java 2D:

- Pie Chart.
- Frequency Histogram.
- Frequency Polygon.
- Ogive.

### Experiment Management

- Save experiments locally.
- Prevent repeated experiment names.
- View all saved experiments in History.
- Open a previous experiment's statistics directly from History.
- Preserve the ordinal setting per qualitative experiment instead of relying on shared static state.

### User Interface

- Dashboard navigation using `CardLayout`.
- Input screen for all supported experiment types.
- Statistics screen with tables and charts.
- Back buttons for smooth navigation.
- History screen with experiment details and direct statistics access.
- Settings screen.
- Night mode support.
- Shared theme system for consistent light and dark styling.

## Project Structure

```text
src/
  app/
    mainN.java
  Charts/
    ColumnChart.java
    FreqHistogram.java
    FreqPolygon.java
    Ogive.java
    PieChart.java
  StatisCalc/
    QualitaveSambles.java
    Quan.java
    QuanSambles.java
    QuanWithClassesAndFreq.java
    StatisTools.java
    StatisticsCalc.java
  storage/
    statisDb.java
  UI/
    ChartsPanel.java
    HistoryPanel.java
    HomePanel.java
    InputPanel.java
    SettingsPanel.java
    StatisPanel.java
    TablesPanel.java
    Theme.java
    ui.java
  UIData/
    ClassFrequencyDataset.java
    NumericSamplesDataset.java
    StatisticsDataset.java
    StringSamplesDataset.java
    Table.java
```

## Architecture

### Logic Layer: `StatisCalc`

This package contains the statistical formulas and calculation flow.

- `Quan` is the base class for quantitative shared behavior.
- `QuanSambles` handles raw numeric samples.
- `QuanWithClassesAndFreq` handles grouped class-frequency data.
- `QualitaveSambles` handles qualitative samples.
- `StatisTools` contains shared helper methods.
- `StatisticsCalc` chooses and stores the correct calculator object.

### Data Preparation Layer: `UIData`

This package prepares calculated results for display.

- `StatisticsDataset` defines the dataset abstraction.
- `NumericSamplesDataset` maps raw numeric results into tables.
- `ClassFrequencyDataset` maps grouped frequency results into tables.
- `StringSamplesDataset` maps qualitative results into tables.
- `Table` creates Swing table components from dataset output.

The project uses `ArrayList` for data structures to keep the data flow readable and flexible. The only remaining array-style signature is Java's required `main(String[] args)`.

### Presentation Layer: `UI`

This package contains the Swing interface.

- `ui` owns the main frame and page navigation.
- `HomePanel` is the main dashboard.
- `InputPanel` collects and validates user input.
- `HistoryPanel` displays saved experiments and can reopen their statistics.
- `SettingsPanel` contains the night mode option.
- `StatisPanel` displays generated statistics and includes a back button.
- `TablesPanel` and `ChartsPanel` organize tabular and visual output.
- `Theme` centralizes UI colors for light and night modes.

### Chart Layer: `Charts`

This package contains custom chart components. Each chart extends a Swing component and overrides `paintComponent` to draw directly with Java graphics.

### Storage Layer: `storage`

This package stores experiments using Java serialization.

- Saved experiments are stored in `database.ser`.
- Experiment names are checked to prevent duplicates.
- Ordinal qualitative settings are saved per experiment.

## Application Workflow

1. The user opens the dashboard.
2. The user selects a new analysis type:
   - Qualitative.
   - Quantitative.
   - Quantitative with classes and frequencies.
3. The input screen validates the experiment name and data.
4. The app prevents duplicate experiment names.
5. The experiment is saved locally.
6. The correct calculator processes the data.
7. The dataset layer prepares the tables.
8. The statistics screen displays tables and charts.
9. The user can return home, open History, or switch night mode from Settings.
10. Previous experiments can be reopened directly from History.

## Input Formats

### Qualitative Data

Enter values separated by commas:

```text
Low, Medium, High, Medium, Low
```

If the data is ordinal, select the Ordinal checkbox before submitting.

### Raw Numeric Samples

Enter the number of classes in the first field:

```text
4
```

Enter numeric samples separated by commas in the second field:

```text
12, 15, 18, 20, 22, 25, 30
```

### Grouped Classes and Frequencies

Enter classes separated by commas:

```text
10 - 19, 20 - 29, 30 - 39
```

Enter matching frequencies:

```text
5, 8, 4
```

The number of classes must match the number of frequencies.

## Build and Run

### Requirements

- JDK 8 or higher.

### Compile

From the project root:

```bash
javac -d bin src/app/mainN.java src/Charts/*.java src/StatisCalc/*.java src/UI/*.java src/UIData/*.java src/storage/*.java
```

### Run

```bash
java -cp bin app.mainN
```

## Important Notes

- The project is built with core Java Swing and Java 2D.
- No external charting libraries are used.
- Saved data is written to `database.ser`.
- Deleting `database.ser` clears saved experiment history.
- The app uses `ArrayList` throughout the project data flow for easier reading and safer resizing.
- Cumulative frequency tables intentionally include one more row than the class count to show endpoint values.

## Current Status

Implemented and verified:

- ArrayList-based statistics flow.
- Separate raw sample and grouped-data formulas.
- Full cumulative-frequency details.
- Qualitative ordinal support.
- Duplicate experiment-name prevention.
- History-to-statistics navigation.
- Back button in the statistics screen.
- Settings screen with night mode.
- Shared UI theme.
- Successful Java compilation.
