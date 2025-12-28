from enum import Enum
from datetime import datetime
from abc import ABC,abstractmethod
import threading

class LogLevel(Enum):
    DEBUG = 1
    INFO = 2
    WARNING = 3
    ERROR = 4
    FATAL = 5

class LogMessage:
    def __init__(self,level:LogLevel,message:str):
        self.timestamp=datetime.now()
        self.level=level
        self.message=message
    
    def format(self)->str:
        return f"{self.timestamp} | {self.level.name} | {self.message}" 
    
class LogSink(ABC):
    @abstractmethod
    def write(self,log_message:LogMessage):
        pass

class ConsoleSink(LogSink):
    def write(self,log_message:LogMessage):
        print(log_message.format())

class FileSink(LogSink):

    def __init__(self, file_path: str):
        self.file_path = file_path

    def write(self, log_message: LogMessage):
        with open(self.file_path, "a") as file:
            file.write(log_message.format() + "\n")

class LoggerConfig:
    def __init__(self,level:LogLevel):
        self.level=level
        self.sinks=[]

    def add_sink(self,sink:LogSink):
        self.sinks.append(sink)

class Logger:
    _instance=None
    _lock=threading.Lock()

    def __init__(self,config:LoggerConfig):
        self.config=config

    @classmethod
    def get_instance(cls,config:LoggerConfig):
        with cls._lock:
            if cls._instance is None:
                cls._instance=Logger(config)
        return cls._instance
    
    def log(self, level: LogLevel, message: str):
        if level.value < self.config.level.value:
            return

        log_message = LogMessage(level, message)
        for sink in self.config.sinks:
            sink.write(log_message)

    def debug(self, message: str):
        self.log(LogLevel.DEBUG, message)

    def info(self, message: str):
        self.log(LogLevel.INFO, message)

    def warning(self, message: str):
        self.log(LogLevel.WARNING, message)

    def error(self, message: str):
        self.log(LogLevel.ERROR, message)

    def fatal(self, message: str):
        self.log(LogLevel.FATAL, message)

if __name__ == "__main__":
    config = LoggerConfig(LogLevel.INFO)
    config.add_sink(ConsoleSink())
    config.add_sink(FileSink("logging_system_app.log"))

    logger = Logger.get_instance(config)

    logger.debug("This will not be logged")
    logger.info("Application started")
    logger.warning("Low memory warning")
    logger.error("Something went wrong")
    logger.fatal("System crashed")