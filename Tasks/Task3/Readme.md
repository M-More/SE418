#### Usage of docker image
1. Fisrt step is to pull the docker image from my dockerhub
`$ docker pull mbwmore/wordladder-maven-plugin`
2. You can use the `$ docker images` command to see a list of all images on your system.
3. Then you can run the new Docker image as a container in interactive mode:
`$ docker run -it mbwmore/wordladder-maven-plugin /bin/bash`
4. Or, for example, you can run the Docker image as a container in background with port `:80` inside the Docker container forwarded to the port `:8080` on the Docker host:
`$ docker run -d -p 8080:80 mbwmore/wordladder-maven-plugin`