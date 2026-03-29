# Java Statistics Calculator & Visualizer

A comprehensive Java Swing application designed to perform descriptive statistical analysis and generate custom visual data representations. This project was built from the ground up without using external charting libraries, focusing on core Java 2D graphics and object-oriented principles.

## 🚀 Features

- **Multi-Type Data Support**: 
  - Raw Numeric Samples (calculates class intervals automatically).
  - Grouped Data (Class frequency distribution).
  - Qualitative Data (String-based samples).
- **In-depth Statistical Logic**: 
  - Measures of Central Tendency: Mean, Median, and Mode.
  - Position Measures: Quartiles (Q1, Q2, Q3) for both raw and grouped data.
  - Frequency Tables: Calculating boundaries, midpoints, relative frequency, and cumulative frequencies (Ascending/Descending).
- **Custom Visualization Engine**: 
  - Hand-rendered charts using `Graphics2D`.
  - Supported Charts: **Pie Chart**, **Frequency Histogram**, **Frequency Polygon**, and **Ogive** (Cumulative Frequency Curve).
- **Dynamic UI**: Responsive dashboard built with `JFrame` and `CardLayout` for seamless switching between different visualizations.

## 🛠️ Technical Architecture

The project follows a clean, modular structure:

* **Logic Layer (`statistics.StatisCalc`)**: Implements the mathematical formulas. Uses inheritance where `Quan` serves as a base class for specialized calculators like `QuanSambles` and `QuanWithClassesAndFreq`.
* **Data Handling (`statistics.UIData`)**: An abstraction layer (`StatisticsDataset`) that prepares data for UI components, ensuring the logic remains decoupled from the presentation.
* **Presentation Layer (`statistics.Charts` & `statistics.UI`)**: Custom components that handle the drawing logic. Each chart class overrides `paintComponent` to calculate coordinates and draw shapes dynamically based on input data.

## 📊 How it Works

The application processes data through a specific pipeline:
1. **Input**: Data is fed into the system (e.g., an array of integers).
2. **Process**: The `StatisTools` and `Quan` classes calculate all necessary statistical parameters.
3. **Map**: `StatisticsDataset` maps these results into tabular formats.
4. **Visualize**: The UI triggers the `Graphics2D` engine to render the statistics visually.

## 💻 Setup

1. Ensure you have **JDK 8** or higher installed.
2. Clone the repository.
3. Compile the source files:
   ```bash
   javac statistics/mainN.java
