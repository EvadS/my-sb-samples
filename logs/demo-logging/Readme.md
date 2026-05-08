

## Log4j log levels in order
| Level | When to use it                                                                 |
|-------|--------------------------------------------------------------------------------|
| TRACE | Extremely detailed execution flow used for deep diagnostics.                   |
| DEBUG | Internal state and decision making for troubleshooting.                        |
| INFO  | Normal, expected application behavior under healthy conditions.                |
| WARN  | The service is an an unusual state that may require attention if it continues. |
| ERROR | An operation failed and functionality was impacted.                            |
| FATAL | An unrecoverable condition that forces the application to shut down.           |