import React from 'react';
import { withRouter } from 'react-router-dom';

function ButtonLink({ history, location, match, to, ...rest }) {
  const handleClick = event => {
    event.preventDefault();
    history.push(to);
  };

  return <button type="button" onClick={handleClick} {...rest} />;
}

export default withRouter(ButtonLink);
