#### Hw3 readme
1. Fisrt step is to pull the docker image from my dockerhub
`$ docker pull mbwmore/microservice-login`
`$ docker pull mbwmore/microservice-wordladder`
2. You can use the `$ docker images` command to see a list of all images on your system.
3. Then you can run the two Docker images:
`$ docker run -d -p 8080:8080 --name login mbwmore/microservice-login`
`$ docker run -d -p 9000:9000 --name wordladder --link login:login mbwmore/microservice-wordladder`
4. Visit `http://localhost:9000` to run the wordladder application.