import subprocess, os

WJ_JAR_LOCATION = os.environ['WJ_JAR_LOCATION']
WJ_URL = os.environ['WJ_URL']
WJ_USER = os.environ['WJ_USER']
WJ_PASS = os.environ['WJ_PASS']

print("Go to project root dir...")
os.system("cd ..")
print("Build the project to the jar file")
subprocess.run(["gradle", "assemble"])
print("Run the project's jar file")
subprocess.run([
    "java",
    "-jar",
    WJ_JAR_LOCATION,
    "--server.port=1408",
    "--spring.jpa.database=POSTGRESQL",
    "--spring.datasource.platform=postgres",
    "--spring.datasource.url=%s" % WJ_URL,
    "--spring.datasource.user=%s" % WJ_USER,
    "--spring.datasource.pass=%s" % WJ_PASS,
    "--spring.jpa.show-sql=true",
    "--spring.jpa.generate-dll=true",
    "--spring.jpa.hibernate.dll-auto=update",
    "--spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true"])

