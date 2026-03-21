#!/bin/bash

# Exit immediately if a command exits with a non-zero status
set -e

echo "--- Compiling Java Code ---"
javac TERRAIN_GENERATOR.java

echo "--- Running Java Terrain Generator ---"
# This will prompt you for the value of n
java TERRAIN_GENERATOR

echo "--- Running Python 3D Visualizer ---"
# Mac uses python3 by default
python3 plot_3d.py

echo "--- Done! ---"