FROM node:20-alpine3.17 AS development

ENV NODE_ENV development
WORKDIR /app
COPY package.json .
#COPY yarn.lock .
RUN yarn install
COPY . .
EXPOSE 3000
CMD [ "yarn", "start" ]