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

...

## Output File Format

...

## Developers

This project has been built, designed and maintained by the following authors:

* [Daniel Lobo](https://github.com/lobo)
* [Agustín Golmar](https://github.com/agustin-golmar)

## Bibliography

__"Novel Type of Phase Transition in a System of Self-Driven Particles."__
Tamás Vicsek, András Czirók, Eshel Ben-Jacob, Inon Cohen, and Ofer Shochet.
_Phys. Rev. Lett. 75, 1226 - Published 7 August 1995. American Physical
Society._
