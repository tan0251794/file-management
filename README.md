
** MINIO SERVER ** 

via Docker:

``docker-compose.yml``
```
version: '3'

services:
  minio:
    container_name: Minio-Server
    command: server /data --console-address ":9001"
    environment:
      - MINIO_ROOT_USER=minioadmin
      - MINIO_ROOT_PASSWORD=minioadmin
    image: minio/minio
    ports:
      - '9000:9000'
      - '9001:9001'
    volumes:
      - './data:/data'
    restart: unless-stopped
```

- start: ``docker compose up -d``
- stop: ``docker compose down``

** FILE-MANAGEMENT SERVER ** 
- Default Address: ``http://localhost:7001``
- APIs:
    + ...
