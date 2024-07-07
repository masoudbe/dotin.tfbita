import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import StringValue from './string-value';
import StringValueDetail from './string-value-detail';
import StringValueUpdate from './string-value-update';
import StringValueDeleteDialog from './string-value-delete-dialog';

const StringValueRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<StringValue />} />
    <Route path="new" element={<StringValueUpdate />} />
    <Route path=":id">
      <Route index element={<StringValueDetail />} />
      <Route path="edit" element={<StringValueUpdate />} />
      <Route path="delete" element={<StringValueDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default StringValueRoutes;
