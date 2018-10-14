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

## Baking an AMI
Ensure you have an IAM user with full access to ec2 and export the user access keys
```
export AWS_ACCESS_KEY_ID=xxx
export AWS_SECRET_ACCESS_KEY=xxxx
```

run the following command

```
packer build -var 'source_deb_file=relative/path/to/deb' packer.json
```
