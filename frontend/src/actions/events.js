import { createAction } from 'redux-starter-kit';

export const createEventInitiatedAction = createAction(
  'events/createEventInitiated'
);
export const createEventSuccessAction = createAction(
  'events/createEventSuccess'
);
export const createEventErrorAction = createAction('events/createEventError');

export const fetchEventInitiatedAction = createAction(
  'events/fetchEventInitiated'
);
export const fetchEventSuccessAction = createAction('events/fetchEventSuccess');
export const fetchEventErrorAction = createAction('events/fetchEventError');

export const fetchEventsInitiatedAction = createAction(
  'events/fetchEventsInitiated'
);
export const fetchEventsSuccessAction = createAction(
  'events/fetchEventsSuccess'
);
export const fetchEventsErrorAction = createAction('events/fetchEventsError');

export const createEvent = event => dispatch => {
  dispatch(createEventInitiatedAction());

  const fetchOptions = {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(event)
  };

  fetch('/api/events', fetchOptions)
    .then(res => {
      if (res.status !== 201) {
        throw Error(`Couldn't create event`);
      }

      dispatch(createEventSuccessAction());
      dispatch(fetchEvents());
    })
    .catch(_ => dispatch(createEventErrorAction()));
};

export const fetchEvents = () => dispatch => {
  dispatch(fetchEventsInitiatedAction());

  fetch('/api/events')
    .then(res => {
      if (res.status !== 200) {
        throw Error(`Couldn't fetch events`);
      }

      return res.json();
    })
    .then(res => dispatch(fetchEventsSuccessAction(res)))
    .catch(_ => dispatch(fetchEventsErrorAction()));
};

export const fetchEvent = id => dispatch => {
  dispatch(fetchEventInitiatedAction());

  fetch(`/api/events/${id}`)
    .then(res => {
      if (res.status !== 200) {
        throw Error(`Couldn't fetch event with ID ${id}`);
      }

      return res.json();
    })
    .then(res => dispatch(fetchEventSuccessAction(res)))
    .catch(_ => dispatch(fetchEventErrorAction()));
};
