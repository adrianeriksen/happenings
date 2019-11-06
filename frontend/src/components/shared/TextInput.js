import React from 'react';

function TextInput({ id, label, name, register, type = 'text', ...rest }) {
  return (
    <div className="form-group">
      <label htmlFor={id}>{label}</label>
      <input id={id} type={type} name={name} ref={register} {...rest} />
    </div>
  );
}

export default TextInput;
