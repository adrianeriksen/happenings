import React from 'react';

function TextInput({
  id,
  label,
  name,
  value,
  handleChange,
  handleBlur,
  type = 'text',
  ...rest
}) {
  return (
    <div className="form-group">
      <label for={id}>{label}</label>
      <input
        id={id}
        type={type}
        name={name}
        value={value}
        onChange={handleChange}
        onBlur={handleBlur}
        {...rest}
      />
    </div>
  );
}

export default TextInput;
