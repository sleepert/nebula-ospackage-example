# nebula-ospackage-example

Basic usage for nebula-ospackage. This will create a .deb image for installing a simple java webapp on your debian distribution. 

## Usage

```
gradle clean build createDeb
```
Your debian file will be created in the build/distributions folder

to install:
```
sudo apt install ./<path to deb>
```

After installation simple start the service

```
service webfluxapp start
```

service will print "hello world" every second 10 times

localhost:8091/hello
