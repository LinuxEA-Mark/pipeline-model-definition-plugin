package org.jenkinsci.plugins.pipeline.modeldefinition.ast;

import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jenkinsci.plugins.pipeline.modeldefinition.validator.ModelValidator;

/**
 * A container for one or more {@link ModelASTOption}s
 *
 * @author Andrew Bayer
 */
public final class ModelASTOptions extends ModelASTElement {
    private List<ModelASTOption> options = new ArrayList<ModelASTOption>();

    public ModelASTOptions(Object sourceLocation) {
        super(sourceLocation);
    }

    @Override
    public JSONObject toJSON() {
        final JSONArray a = new JSONArray();
        for (ModelASTOption option : options) {
            a.add(option.toJSON());
        }
        return new JSONObject().accumulate("options", a);
    }

    @Override
    public void validate(final ModelValidator validator) {
        validator.validateElement(this);
        for (ModelASTOption option : options) {
            option.validate(validator);
        }
    }

    @Override
    public String toGroovy() {
        StringBuilder result = new StringBuilder("options {\n");
        for (ModelASTOption option : options) {
            result.append(option.toGroovy()).append("\n");
        }
        result.append("}\n");
        return result.toString();
    }

    @Override
    public void removeSourceLocation() {
        super.removeSourceLocation();
        for (ModelASTOption option : options) {
            option.removeSourceLocation();
        }
    }

    public List<ModelASTOption> getOptions() {
        return options;
    }

    public void setOptions(List<ModelASTOption> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "ModelASTOptions{" +
                "options=" + options +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        ModelASTOptions that = (ModelASTOptions) o;

        return getOptions() != null ? getOptions().equals(that.getOptions()) : that.getOptions() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getOptions() != null ? getOptions().hashCode() : 0);
        return result;
    }
}
