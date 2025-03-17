#!/bin/sh

sbt run &
ngrok http 9000
