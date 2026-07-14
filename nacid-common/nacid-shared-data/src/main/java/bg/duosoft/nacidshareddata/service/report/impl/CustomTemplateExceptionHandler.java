package bg.duosoft.nacidshareddata.service.report.impl;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Created by Raya
 * 11.01.2021
 */
@Slf4j
public class CustomTemplateExceptionHandler implements TemplateExceptionHandler {

    public void handleTemplateException(TemplateException te, Environment env, java.io.Writer out)
        throws TemplateException {
        try {
            out.write("<span style='color:red'>error in receipt</span>");
            log.error("Error in template", te);
        } catch (IOException e) {
            throw new TemplateException("Failed to print error message. Cause: " + e, env);
        }
    }

}
