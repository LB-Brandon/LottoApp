# Lotto Number Picker App

<img src="https://github.com/LB-Brandon/LottoApp/assets/84883277/b4cb8fb9-1abb-4bc9-acd7-d715657b3285.png" width="200" height="400"/>


## Overview

The Lotto Number Picker App is a simple Android application that allows users to pick and display random lottery numbers. Users can select up to 5 numbers, and the app generates a set of random numbers while avoiding duplicates. The selected numbers are displayed on the screen with distinct background colors based on predefined ranges.

## Features

- **Clear Button:** Resets the selected numbers, hides the displayed numbers, and sets the NumberPicker back to its initial value.

- **Add Button:** Allows users to add a selected number to their choices. Displays the selected numbers on the screen with distinct background colors.

- **Run Button:** Generates a set of random numbers, ensuring they do not overlap with the user's selected numbers. Displays the random numbers on the screen with corresponding background colors.

## Code Highlights

### Number Ranges

```kotlin
val NUMBER_RANGE_1_TO_10 = 1..10
val NUMBER_RANGE_11_TO_20 = 11..20
val NUMBER_RANGE_21_TO_30 = 21..30
val NUMBER_RANGE_31_TO_40 = 31..40
```

Predefined number ranges for setting distinct background colors.

### Initialization

```kotlin
numberPicker.apply {
    minValue = 1
    maxValue = 45
}
```

Setting up the NumberPicker's minimum and maximum values during initialization.

### Random Number Generation

```kotlin
private fun getRandom(): List<Int> {
    val numbers = (1..45).filter { it !in selectedNumbers }
    return (selectedNumbers + numbers.shuffled().take(6 - selectedNumbers.size)).sorted()
}
```

Generating a set of random numbers without duplicates.

### UI Updates

```kotlin
private fun setNumberBackgroundColorByRange(num: Int, textView: TextView) {
    val backgroundColor = when (num) {
        in NUMBER_RANGE_1_TO_10 -> R.drawable.circle_yellow
        in NUMBER_RANGE_11_TO_20 -> R.drawable.circle_blue
        in NUMBER_RANGE_21_TO_30 -> R.drawable.circle_red
        in NUMBER_RANGE_31_TO_40 -> R.drawable.circle_gray
        else -> R.drawable.circle_green
    }
    textView.background = ContextCompat.getDrawable(this, backgroundColor)
}
```

Setting the background color of TextViews based on the ranges of selected and random numbers.

## How to Use

1. Press the "Add" button to select up to 5 numbers.
2. Press the "Run" button to generate a set of random numbers.
3. Use the "Clear" button to reset selections and start again.

## Technologies Used

- Android Studio
- Kotlin
