import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import CustomJustification from './custom-justification';
import CustomJustificationDetail from './custom-justification-detail';
import CustomJustificationUpdate from './custom-justification-update';
import CustomJustificationDeleteDialog from './custom-justification-delete-dialog';

const CustomJustificationRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<CustomJustification />} />
    <Route path="new" element={<CustomJustificationUpdate />} />
    <Route path=":id">
      <Route index element={<CustomJustificationDetail />} />
      <Route path="edit" element={<CustomJustificationUpdate />} />
      <Route path="delete" element={<CustomJustificationDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default CustomJustificationRoutes;
