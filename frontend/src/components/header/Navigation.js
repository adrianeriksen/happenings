import React from 'react';
import ButtonLink from '../shared/ButtonLink';

const Navigation = ({ deauthenticate, isAuthenticated }) => (
  <div className="primary-navigation">
    {isAuthenticated ? (
      <button type="button" onClick={deauthenticate}>
        Log out
      </button>
    ) : (
      <>
        <ButtonLink to="/login">Log in</ButtonLink>
        <ButtonLink to="/signup">Sign up</ButtonLink>
      </>
    )}
  </div>
);

export default Navigation;
