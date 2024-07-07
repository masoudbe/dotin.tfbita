import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import LongValue from './long-value';
import LongValueDetail from './long-value-detail';
import LongValueUpdate from './long-value-update';
import LongValueDeleteDialog from './long-value-delete-dialog';

const LongValueRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<LongValue />} />
    <Route path="new" element={<LongValueUpdate />} />
    <Route path=":id">
      <Route index element={<LongValueDetail />} />
      <Route path="edit" element={<LongValueUpdate />} />
      <Route path="delete" element={<LongValueDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default LongValueRoutes;
