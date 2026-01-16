# Repository Guidelines

## Project Structure & Module Organization
- `src/main/java/frc/robot/` contains the robot code (entry points: `Main`, `Robot`, `RobotContainer`).
- `src/main/java/frc/robot/commands/` and `src/main/java/frc/robot/subsystems/` hold command-based logic.
- `src/main/deploy/` stores deployable assets and swerve configuration JSON files (e.g., `swerve/`, `modules/`).
- `vendordeps/` lists vendor libraries (Phoenix, REV, YAGSL, etc.).
- Gradle wrapper and configuration live at `gradlew*`, `build.gradle`, and `gradle/`.

## Build, Test, and Development Commands
- `./gradlew build` — compiles and assembles the project (Java 17).
- `./gradlew test` — runs JUnit 5 tests on the desktop JVM.
- `./gradlew deploy` — deploys code and `src/main/deploy` assets to the RoboRIO.
- `./gradlew simulateJava` — runs WPILib simulation (GUI + driver station enabled in `build.gradle`).

## Coding Style & Naming Conventions
- Java 17 source/target; follow standard WPILib Java conventions.
- Indentation: 4 spaces; braces on the same line.
- Class names in `PascalCase`, methods/fields in `camelCase`, constants in `UPPER_SNAKE_CASE` (see `Constants.java`).
- Keep configuration JSON files lowercase with descriptive names (e.g., `frontleft.json`).

## Testing Guidelines
- Framework: JUnit 5 (`org.junit.jupiter`).
- Place tests in `src/test/java/` mirroring production packages.
- Name tests `*Test` and keep them fast and deterministic.
- Run locally with `./gradlew test` before deploying.

## Commit & Pull Request Guidelines
- No existing commit history in this repository; follow Conventional Commits if starting fresh (e.g., `feat: add auto routine`).
- PRs should include a clear description, testing notes (`./gradlew test` or simulation results), and any relevant screenshots or log snippets.
- Link issues or tasks when applicable and call out changes to `src/main/deploy/` assets.

## Configuration & Deployment Notes
- Team number is read from `.wpilib/wpilib_preferences.json` or CLI overrides; ensure it is set before `./gradlew deploy`.
- Review `src/main/deploy/swerve/` JSONs when changing module hardware or PID values.
