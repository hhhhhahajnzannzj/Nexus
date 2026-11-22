# Nexus

Nexus is an Android project exploring a guided Wireless debugging pairing experience.

## Current status

This repository is an early prototype. The initial app focuses on explaining Android Wireless debugging and providing a notification-based pairing-code entry UI.

**Important:** Entering a pairing code in the notification does not itself perform ADB pairing or grant elevated privileges. Those capabilities require a real, authenticated ADB pairing implementation and a compatible service. Nexus does not currently claim Shizuku parity.

## Build

The Android app is built with Gradle and GitHub Actions. See the workflow under `.github/workflows/build.yml` once added.

## Safety and privacy

Pairing codes are sensitive. The app should not log or persist them.
