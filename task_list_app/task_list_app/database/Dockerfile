FROM postgres:latest

# Set the environment variables
ENV POSTGRES_DB task_data
ENV POSTGRES_USER task_user
ENV POSTGRES_PASSWORD task_pass

# Specify the location for the PostgreSQL data in the container
ENV PGDATA /var/lib/postgresql/data

# Copy the SQL scripts to the container
COPY init.sql /docker-entrypoint-initdb.d/
