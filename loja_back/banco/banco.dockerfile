FROM postgres:latest
RUN apt-get update && apt-get -y install cron
RUN cd /etc/cron.d/ && echo "* * * * * pg_dump -U postgres sou_qa > /var/lib/postgresql/souqa.sql" > dumpcron
RUN chmod 0644 /etc/cron.d/dumpcron
RUN crontab /etc/cron.d/dumpcron
RUN cron
# /etc/init.d/cron status  // verifica status cron


