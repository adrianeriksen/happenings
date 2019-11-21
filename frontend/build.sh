#!/bin/bash
npm run build
docker build -t happenings-frontend .

# docker run --name happenings-frontend -d -p 4000:80 happenings-frontend

echo "Frontend build successful \o/"
echo "Start the container by running:"
echo "$ docker run --name happenings-frontend -d -p 4000:80 happenings-frontend"