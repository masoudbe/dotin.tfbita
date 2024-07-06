import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import CustomJustificationChild from './custom-justification-child';
import CustomJustificationChildDetail from './custom-justification-child-detail';
import CustomJustificationChildUpdate from './custom-justification-child-update';
import CustomJustificationChildDeleteDialog from './custom-justification-child-delete-dialog';

const CustomJustificationChildRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<CustomJustificationChild />} />
    <Route path="new" element={<CustomJustificationChildUpdate />} />
    <Route path=":id">
      <Route index element={<CustomJustificationChildDetail />} />
      <Route path="edit" element={<CustomJustificationChildUpdate />} />
      <Route path="delete" element={<CustomJustificationChildDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default CustomJustificationChildRoutes;
