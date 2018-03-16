![...](resources/image/readme-header.png)

# System Simulation: Cellular Automaton

## Build

To build the project, it is necessary to have _Maven +3.5.0_, and
_Java SE 8 Release_ installed. Then, run:

```
$ mvn clean package
```

This will generate a _\*.jar_ in the `target` folder.

## Execute

In the root folder (after build):

```
$ java -jar target/tp2-1.0-SNAPSHOT.jar cycles dt N L RC V noise
```

Where:

* `[cycles]` is the number of cycles. Integer.
* `[dt]` is the delta time, if too big: simulations will go fast, if too littl: simulations will go slower. Double.
* `[N]` is the number of particles. Integer.
* `[L]` is the length of the side of the square. Double.
* `[RC]` is the radius. Double.
* `[V]` is the speed for all the particles. Double.
* `[noise]` is the noise. Double.

## Help

```
$ java -jar target/tp2-1.0-SNAPSHOT.jar help
```

## Input Files Format

There are no input files in this project. All the 

## Output File Format

The output is a `.txt` file that has the following pattern multiplied by the number of cycles.

```
number_of_particles
number_of_cycle
position_x_particle_1 position_y_particle_1 angle
position_x_particle_2 position_y_particle_2 angle
position_x_particle_3 position_y_particle_3 angle
...
position_x_particle_n position_y_particle_n angle
```

which is then read by a Scientific visualization and analysis software for atomistic simulation data, called [Ovito](http://www.ovito.org/).

## Developers

This project has been built, designed and maintained by the following authors:

* [Daniel Lobo](https://github.com/lobo)
* [Agustín Golmar](https://github.com/agustin-golmar)

## Videos

Some videos of the animations are listed here:

* [Video1](https://www.youtube.com/watch?v=PR3xM9J5YUc)
* [Video2](https://www.youtube.com/watch?v=6k6riZO4luU)
* [Video3](https://www.youtube.com/watch?v=-0rk5B2qxZQ)

## Bibliography

__"Novel Type of Phase Transition in a System of Self-Driven Particles."__
Tamás Vicsek, András Czirók, Eshel Ben-Jacob, Inon Cohen, and Ofer Shochet.
_Phys. Rev. Lett. 75, 1226 - Published 7 August 1995. American Physical
Society._
