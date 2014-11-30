package src.john01dav.votifiertester.gui;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class VoteFileFilter extends FileFilter{

    @Override
    public boolean accept(File f) {
        if(f.isDirectory())
            return true;

        return f.getName().toLowerCase().endsWith(".vtc");
    }

    @Override
    public String getDescription() {
        return "Votifier Tester Configuration (*.vtc)";
    }
}
