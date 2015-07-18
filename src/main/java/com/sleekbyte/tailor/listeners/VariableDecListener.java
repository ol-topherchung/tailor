package com.sleekbyte.tailor.listeners;

import com.sleekbyte.tailor.antlr.SwiftBaseListener;
import com.sleekbyte.tailor.antlr.SwiftParser;
import com.sleekbyte.tailor.common.Location;
import com.sleekbyte.tailor.common.Messages;
import com.sleekbyte.tailor.utils.CharFormatUtil;
import com.sleekbyte.tailor.utils.ListenerUtil;

/**
 * Listener for variable declarations.
 */
public class VariableDecListener extends SwiftBaseListener {

    private ListenerHelper helper;

    /**
     * Creates a VariableDecListener object and stores the listener helper.
     *
     * @param helper {@link ListenerHelper} helper class for listeners
     */
    VariableDecListener(ListenerHelper helper) {
        this.helper = helper;
    }

    @Override
    public void enterIdentifier(SwiftParser.IdentifierContext ctx) {
        String variableName = ctx.getText();
        Location location = ListenerUtil.getContextStartLocation(ctx);

        if (!CharFormatUtil.isLowerCamelCase(variableName)) {
            helper.getPrinter().error(Messages.VARIABLE + Messages.NAMES + Messages.LOWER_CAMEL_CASE, location);
        }
        helper.verifyNameLength(Messages.VARIABLE + Messages.NAME, helper.getMaxLengths().maxNameLength, ctx);
    }
}
