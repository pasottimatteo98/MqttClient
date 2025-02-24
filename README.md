# Smart City MQTT Client

## Overview
This project implements an MQTT client for a Smart City platform. It includes a Publisher and Subscriber that communicate with the MQTT broker at `smartcity-challenge.edu-al.unipmn.it:1883`.

The application executes Linux commands (ps, df, ipcs, vmstat) and publishes their results to the MQTT topic.

## Components

### Publisher
The Publisher connects to the MQTT broker and periodically executes predefined system commands from `AcceptedCommands.json`. The output of these commands is published to the topic `pissir/20035991/1`.

### Subscriber
The Subscriber listens to incoming commands on topics `pissir/all/cmd` and `pissir/20035991/1/cmd`. When a command is received, it validates if the command is allowed (by checking `AcceptedCommands.json`), executes it, and publishes the result.

### Supporting Classes
- `Results`: Stores the command executed and its result.
- `CmdAccepts`: Manages the list of accepted commands.
- `SubscribeCallback`: Handles incoming MQTT messages.

## Configuration
The accepted commands are defined in `AcceptedCommands.json`:
```json
{"accepted":["ps","df","ipcs","vmstat"]}
```

## Connection Details
- Broker URL: `tcp://smartcity-challenge.edu-al.unipmn.it:1883`
- Username: `pissir`
- Password: `pissir2020`
- Publication Topic: `pissir/20035991/1`
- Subscription Topics: `pissir/all/cmd` and `pissir/20035991/1/cmd`

## Usage

### Running the Publisher
```bash
java Publisher [broker-url] [command]
```
- Without arguments: Connects to the default broker and periodically executes all accepted commands
- With broker-url: Connects to the specified broker
- With broker-url and command: Executes the specified command once

### Running the Subscriber
```bash
java Subscriber [host]
```
- Without arguments: Connects to the default broker
- With host: Connects to the specified host

## Dependencies
- Eclipse Paho MQTT Client (Licensed under EPL v2.0 and EDL v1.0)
- Google Gson for JSON processing

## Project Structure
- `Publisher.java`: Main publisher implementation
- `Subscriber.java`: Main subscriber implementation
- `Results.java`: Data model for command results
- `CmdAccepts.java`: Data model for accepted commands
- `SubscribeCallback.java`: MQTT message callback handler
- `AcceptedCommands.json`: Configuration file for allowed commands

## License
This project uses components licensed under the Eclipse Public License v2.0 and Eclipse Distribution License v1.0.
