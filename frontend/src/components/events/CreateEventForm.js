import React from 'react';
import useForm from 'react-hook-form';
import TextInput from '../shared/TextInput';

function CreateEventFrom({ createEvent }) {
  const { register, handleSubmit } = useForm();

  const onSubmit = values => {
    createEvent(values);
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <h3>Create new event</h3>
      <TextInput
        label="Title"
        id="create-event-form-title"
        name="title"
        type="text"
        register={register({ required: true })}
      />
      <TextInput
        label="Starts at"
        id="create-event-form-starts-at"
        name="startsAt"
        type="text"
        register={register({ required: true })}
      />
      <TextInput
        label="Ends at"
        id="create-event-form-ends-at"
        name="endsAt"
        type="text"
        register={register}
      />
      <TextInput
        label="Where"
        id="create-event-form-where"
        name="where"
        type="text"
        register={register}
      />
      <TextInput
        label="Description"
        id="create-event-form-description"
        name="description"
        type="text"
        register={register}
      />
      <button type="submit">Create event</button>
    </form>
  );
}

export default CreateEventFrom;
