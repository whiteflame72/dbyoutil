Call ../setEnv.cmd

%COMMAND_DIR%/rDB2XML oracle.jdbc.driver.OracleDriver jdbc:oracle:thin:@192.168.30.223:1521:HELIOMOD4 HLMODUSR hl4sr_Oy %1 %2

:%COMMAND_DIR%/rDB2XML oracle.jdbc.driver.OracleDriver jdbc:oracle:thin:@10.10.50.220:1526:RGRTEST1 HLMODUSR HLMODUSR %1 %2

:pause